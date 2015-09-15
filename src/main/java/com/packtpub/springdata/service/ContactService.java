/**
 * 
 */
package com.packtpub.springdata.service;

import java.util.List;

import com.packtpub.springdata.dto.ContactDTO;
import com.packtpub.springdata.model.Contact;

/**
 * @author herb
 *
 */
public interface ContactService {
	
	public Contact add(ContactDTO added);
	public Contact deleteById(Long id) throws NotFoundException;
	public List<Contact> findAll();
	public Contact findById(Long id) throws NotFoundException;
	public Contact update(ContactDTO updated) throws NotFoundException;
	
}
