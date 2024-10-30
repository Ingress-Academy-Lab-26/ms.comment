package az.ingress.dao.entity;

import az.ingress.model.enums.CommentStatus;
import lombok.*;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
//* u duzelt (wildcard olmasin)
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "comments")
@FieldNameConstants
public class CommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private Long productId;

    private String content;

    @Enumerated(EnumType.STRING)
    private CommentStatus status;


    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommentEntity commentEntity = (CommentEntity) o;
        return Objects.equals(id, commentEntity.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
