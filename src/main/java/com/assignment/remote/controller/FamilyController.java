package com.assignment.remote.controller;

import com.assignment.remote.annotation.ApiController;
import com.assignment.remote.annotation.ValidateData;
import com.assignment.remote.dto.BaseRequestBody;
import com.assignment.remote.dto.BaseResponseBody;
import com.assignment.remote.dto.ParentDto;
import com.assignment.remote.service.FamilyService;
import com.assignment.remote.utils.UrlConstraint;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Api(value = "Family Api", tags = {"Api For Family Info"})
@ApiController
@RequestMapping(UrlConstraint.ROOT)
public class FamilyController {


    private final FamilyService familyService;

    public FamilyController(FamilyService familyService) {
        this.familyService = familyService;
    }

    @ApiOperation(value = "Create family api", response = BaseResponseBody.class, responseContainer = "Object")
    @ValidateData
    @PostMapping(UrlConstraint.FamilyApiManagement.create)
    public BaseResponseBody<?> createFamily(@RequestBody @Valid BaseRequestBody<ParentDto> parent, BindingResult result, HttpServletRequest request, HttpServletResponse response) {
        return familyService.create(parent.getData());
    }

    @ApiOperation(value = "Update family api", response = BaseResponseBody.class, responseContainer = "Object")

    @ValidateData
    @PutMapping(UrlConstraint.FamilyApiManagement.update)
    public BaseResponseBody<?> updateFamily(@PathVariable("id") Long id, @RequestBody @Valid BaseRequestBody<ParentDto> parent, BindingResult result, HttpServletRequest request, HttpServletResponse response) {
        return familyService.update(id, parent.getData());
    }

    @ApiOperation(value = "Delete family api", response = BaseResponseBody.class, responseContainer = "Object")

    @DeleteMapping(UrlConstraint.FamilyApiManagement.delete)
    public BaseResponseBody<?> deleteFamily(@PathVariable("id") Long id, HttpServletRequest request, HttpServletResponse response) {
        return familyService.delete(id);
    }

    @ApiOperation(value = "Get All family api", response = BaseResponseBody.class, responseContainer = "Object")
    @GetMapping(UrlConstraint.FamilyApiManagement.getAll)
    public BaseResponseBody<?> getAll() {
        return familyService.getAll();
    }

}
