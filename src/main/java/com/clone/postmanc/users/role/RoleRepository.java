package com.clone.postmanc.users.role;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
  public Role findByAuthority(String roleName);

  public boolean existsByAuthority(String roleName);
}
