package com.raspberry.movieinfo.repository;

import com.raspberry.movieinfo.entity.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    Page<Movie> findByMcodeGreaterThan(Long mcode, Pageable pb);
}
