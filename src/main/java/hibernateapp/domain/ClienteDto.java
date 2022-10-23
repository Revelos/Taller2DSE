package hibernateapp.domain;

import hibernateapp.entity.Cliente;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

/**
 * A DTO for the {@link Cliente} entity
 */
@AllArgsConstructor
@Getter
@ToString
public class ClienteDto implements Serializable {
    private final Long id;
    private final String nombre;
    private final String apellido;
    private final Integer edad;
    private final Integer telefono;
    private final String email;
}