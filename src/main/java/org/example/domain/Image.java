package org.example.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "images")
public class Image implements Serializable {

    public Image(String link, String name, Long size) {
        this.link = link;
        this.name = name;
        this.size = size;
    }

    @Id
    private String link;

    private String name;

    private Long size;

    @ToString.Exclude
    @ManyToMany()
    @JoinTable(
            name = "message_image_links",
            joinColumns = @JoinColumn(name = "image_link"),
            inverseJoinColumns = @JoinColumn(name = "message_id"))
    private List<Message> messages = new ArrayList<>();
}
