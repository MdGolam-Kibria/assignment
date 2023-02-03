package com.assignment.remote.service;

import com.assignment.remote.dto.BaseResponseBody;
import com.assignment.remote.dto.ParentDto;

public interface FamilyService {
    BaseResponseBody<?> create(ParentDto parentDto);

    BaseResponseBody<?> update(Long id, ParentDto parentDto);

    BaseResponseBody<?> delete(Long id);

    BaseResponseBody<?> getAll();

}
