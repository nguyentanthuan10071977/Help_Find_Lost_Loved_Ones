package com.hfl.repository;
import com.hfl.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
public interface PostRepository extends JpaRepository<Post,Long> {
    List<Post> findByTitleContainingIgnoreCaseOrContentContainingIgnoreCase(String title,String content);
    List<Post> findByLostYearBetween(Integer from,Integer to);
    List<Post> findByLostCircumstanceContainingIgnoreCase(String circumstance);
    List<Post> findByObjectInformationContainingIgnoreCase(String objectInfo);
    @Query("select p from Post p where (:age is null or p.lostAge=:age) and (:year is null or p.lostYear=:year) and (:circ is null or lower(p.lostCircumstance) like lower(concat('%',:circ,'%')))")
    List<Post> advanced(@Param("age") Integer age,@Param("year") Integer year,@Param("circ") String circ);
}
