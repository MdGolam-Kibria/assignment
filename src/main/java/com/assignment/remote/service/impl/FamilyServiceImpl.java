package com.assignment.remote.service.impl;

import com.assignment.remote.dto.BaseResponseBody;
import com.assignment.remote.dto.ParentDto;
import com.assignment.remote.entity.Child;
import com.assignment.remote.entity.Parent;
import com.assignment.remote.repository.ParentRepository;
import com.assignment.remote.service.FamilyService;
import com.assignment.remote.utils.ResponseBuilder;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("familyService")
public class FamilyServiceImpl implements FamilyService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());
    private final ModelMapper modelMapper;
    private final ParentRepository parentRepository;

    public FamilyServiceImpl(ModelMapper modelMapper,
                             ParentRepository parentRepository) {
        this.modelMapper = modelMapper;
        this.parentRepository = parentRepository;
    }

    @Override
    public BaseResponseBody<?> create(ParentDto parentDto) {
        try {
            Parent parent = modelMapper.map(parentDto, Parent.class);
            parent = parentRepository.save(parent);
            return ResponseBuilder.getSuccessResponse(HttpStatus.OK, "Family information successfully created", parent);
        } catch (Exception e) {
            logger.error("Something went wrong during create family info :{}", e.getMessage(), e);
            return ResponseBuilder.getFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        }
    }

    @Override
    public BaseResponseBody<?> update(Long id, ParentDto parentDto) {
        try {
            Parent updateAbleParent = parentRepository.findByIdAndIsActiveTrue(id);
            if (updateAbleParent == null) {
                return ResponseBuilder.getFailureResponse(HttpStatus.BAD_REQUEST, "Didn't find any info by the provided Id");
            }
            modelMapper.map(parentDto, updateAbleParent);
            parentRepository.save(updateAbleParent);
            if (updateAbleParent != null) {
                return ResponseBuilder.getFailureResponse(HttpStatus.OK, "Successfully updated the family information");
            }
            return ResponseBuilder.getFailureResponse(HttpStatus.CONFLICT, "Something went during update family information");
        } catch (Exception e) {
            logger.error("Something went wrong during update family info :{}", e.getMessage(), e);
            return ResponseBuilder.getFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        }
    }

    @Override
    public BaseResponseBody<?> delete(Long id) {
        try {
            Parent deleteAbleParent = parentRepository.findByIdAndIsActiveTrue(id);
            if (deleteAbleParent == null) {
                return ResponseBuilder.getFailureResponse(HttpStatus.BAD_REQUEST, "Didn't find any info by the provided Id");
            }
            deleteAbleParent.setIsActive(false);
            deleteAbleParent.getChildren().forEach(child -> child.setIsActive(false));
            parentRepository.save(deleteAbleParent);
            return ResponseBuilder.getFailureResponse(HttpStatus.OK, "Successfully deleted");
        } catch (Exception e) {
            logger.error("Something went wrong during delete family info :{}", e.getMessage(), e);
            return ResponseBuilder.getFailureResponse(HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        }
    }

    @Override
    public BaseResponseBody<?> getAll() {
        List<Parent> familyInfos = parentRepository.findAllByIsActiveTrue();
        if (familyInfos.isEmpty()) {
            return ResponseBuilder.getFailureResponse(HttpStatus.NO_CONTENT, "Didn't find family info");
        }
        return ResponseBuilder.getFailureResponse(HttpStatus.OK, "Family info retrieved successfully", familyInfos);
    }
}
