package com.assignment.remote.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ParentDto {
    @Valid
    private String firstName;
    @Valid
    private String lastName;
    @Valid
    private String street;
    @Valid
    private String city;
    @Valid
    private String state;
    @Valid
    private String zip;
    private List<@Valid ChildDto> children;
}
