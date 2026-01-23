package com.jayzebra.surveys.adapter.output.entity;


import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Survey {
    @Id
    @NotBlank(message = "Id should not be blank")
    private String id;
    @NotBlank(message = "title should not be blank")
    private String title;
    @ElementCollection
    private List<String> questions;

}
