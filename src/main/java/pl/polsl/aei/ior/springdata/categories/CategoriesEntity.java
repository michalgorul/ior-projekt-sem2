package pl.polsl.aei.ior.springdata.categories;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@EqualsAndHashCode
@Getter
@Setter
@Entity
@Table(name = "Categories", schema = "store")
public class CategoriesEntity {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID categoryId;

    @Basic
    @Column(name = "category_name")
    private String categoryName;

}
