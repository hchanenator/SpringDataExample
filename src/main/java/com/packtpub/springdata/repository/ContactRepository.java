/**
 * 
 */
package com.packtpub.springdata.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.packtpub.springdata.model.Contact;

/**
 * @author herb
 *
 */
public interface ContactRepository extends JpaRepository<Contact, Long> {

}
