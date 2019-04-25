package com.rybak.education.pirogok.repo;

import com.rybak.education.pirogok.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Long> {

    List<Post> findByTag(String tag);
}
