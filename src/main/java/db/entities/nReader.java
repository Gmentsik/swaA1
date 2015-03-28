package db.entities;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Gergely on 25.03.2015.
 */
@Entity
@NamedQuery(name="finAllnReaders", query = "SELECT e FROM nReader e")
public class nReader implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @NotNull
    private String fName;

    @NotNull
    private String lName;

    @Temporal(TemporalType.DATE)        //signifies a date attribute
    @Past                               //value should precede the current date, succeed is @Future
    private Date birth;

    @NotNull
    @Pattern(regexp = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\."
            + "[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9]"
            + "(?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9]"
            + "(?:[a-z0-9-]*[a-z0-9])?",
            message = "{invalid.email}")
    private String email;


}
