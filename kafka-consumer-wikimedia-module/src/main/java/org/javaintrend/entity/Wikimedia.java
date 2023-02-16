package org.javaintrend.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.persistence.Lob;

@Data
@Document(collection = "wikimedia")
public class Wikimedia {

    @Lob
    private String wikiEventData;

}
