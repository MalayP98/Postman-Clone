package com.clone.postmanc.exchange.response;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResponseRepository extends JpaRepository<Response, Long> {

  // @Query("select count(r) from Response r where r.requestId = :#{#response.requestId}")
  // public int exists(@Param("response") Response response);

  public List<Response> findByRequestId(long requestId);

}
