package flaviodeangelis.u6w2d2.repository;

import flaviodeangelis.u6w2d2.entities.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogRepository extends JpaRepository<BlogPost, Long> {
}
