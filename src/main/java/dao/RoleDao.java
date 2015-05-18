package dao;

import java.util.List;

import entity.Role;

public interface RoleDao {
	
	List<Role> getRoles();
	
	List<Role> getRoleById(int id);

}
