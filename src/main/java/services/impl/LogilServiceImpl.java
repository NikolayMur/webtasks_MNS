package services.impl;

import java.util.List;

import dao.impl.AccountDaoImpl;
import dao.impl.RoleDaoImpl;
import dao.AccountDao;
import dao.RoleDao;
import entity.Account;
import entity.Role;
import exeptions.InvalidDataException;
import services.LoginService;

public class LogilServiceImpl implements LoginService{
	
	private AccountDao accountDao = new AccountDaoImpl();
	private RoleDao roleDao = new RoleDaoImpl();

	@Override
	public Account login(String login, String password, int role) throws InvalidDataException {
		Account accountByLogin = accountDao.getAccountByLogin(login);
		
		if (accountByLogin.getId() == 0)
			throw new InvalidDataException("Account not found");
		
		if (!password.equals(accountByLogin.getPassword()))
			throw new InvalidDataException("Password is failed");
		
		List<Role> roles = accountByLogin.getRoles();
		List<Role> currentRole = roleDao.getRoleById(role);


        //Предусмотреть правильную!!!!!!
        //На уровне Account


        // неправильная валидация!!!!!!!!!!
        for (Role role1 : roles) {
            if (role1.getId() == currentRole.get(0).getId())
                return accountByLogin;
        }
        throw new InvalidDataException("role");
    }
}
