package flaviodeangelis.u6w2d2.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "blog_posts")
public class BlogPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String category;
    private String title;
    private String cover;
    private String content;
    private int readingTime;
    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;

    @Override
    public String toString() {
        return "flaviodeangelis.u6w2d2.entities.BlogPost{" +
                "id=" + id +
                ", category='" + category + '\'' +
                ", title='" + title + '\'' +
                ", cover='" + cover + '\'' +
                ", content='" + content + '\'' +
                ", readingTime=" + readingTime +
                '}';
    }
}
