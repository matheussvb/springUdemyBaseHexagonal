package br.com.matheussvb.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RestResponse<T> {
    private final T data;
}
