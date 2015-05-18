package dao.impl;

import dao.RoleDao;
import database.DataService;
import entity.Role;

import java.util.List;

public class RoleDaoImpl implements RoleDao{
	private DataService dataService = new DataService();

	@Override
	public List<Role> getRoles() {	
		return dataService.getAllRoles();
	}

	@Override
	public List<Role> getRoleById(int id) {
		
		return dataService.getRolesById(id);
	}
	
	

}
