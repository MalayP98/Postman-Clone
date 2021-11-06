package com.clone.postmanc.request;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestRepositiory extends JpaRepository<Request, Long> {

  @Query("select r from Request r where r.endpoint = :#{#request.endpoint} and r.userId = :#{#request.userId}")
  public List<Request> exists(@Param("request") Request request);
}
