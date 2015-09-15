/**
 * 
 */
package com.packtpub.springdata.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

/**
 * @author herb
 *
 */
@Entity
@Table(name = "contacts")
public class Contact {
	
	public static final int MAX_LENGTH_EMAIL_ADDRESS = 100;
    public static final int MAX_LENGTH_FIRST_NAME = 50;
    public static final int MAX_LENGTH_LAST_NAME = 100;
    public static final int MAX_LENGTH_PHONE_NUMBER = 30;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private Address address;

	@Column(name = "email_address", length = 100)
	private String emailAddress;

	@Column(name = "first_name", nullable = false, length = 50)
	private String firstName;

	@Column(name = "last+_name", nullable = false, length = 100)
	private String lastName;

	@Column(name = "phone_number", length = 30)
	private String phoneNumber;

	@Version
	private long version;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	public void update(final String firstName, final String lastName, final String emailAddress,
			final String phoneNumber) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAddress = emailAddress;
		this.phoneNumber = phoneNumber;
	}

	public void updateAddress(final String streetAddress, final String postCode, final String postOffice,
			final String state, final String country) {
		if (address == null) {
			address = new Address();
		}

		address.update(streetAddress, postCode, postOffice, state, country);
	}

	public static class Builder {

		private Contact built;

		public Builder(String firstName, String lastName) {
			built = new Contact();
			built.firstName = firstName;
			built.lastName = lastName;
		}

		public Builder address(String streetAddress, String postCode, String postOffice, String state, String county) {
			Address address = Address.getBuilder(streetAddress, postCode, postOffice).state(state).country(county)
					.build();
			built.address = address;
			return this;
		}

		public Builder emailAddress(String emailAddress) {
			built.emailAddress = emailAddress;
			return this;
		}

		public Builder phoneNumber(String phoneNumber) {
			built.phoneNumber = phoneNumber;
			return this;
		}

		public Contact build() {
			return built;
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((emailAddress == null) ? 0 : emailAddress.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
		result = prime * result + (int) (version ^ (version >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Contact other = (Contact) obj;
		if (emailAddress == null) {
			if (other.emailAddress != null)
				return false;
		} else if (!emailAddress.equals(other.emailAddress))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (phoneNumber == null) {
			if (other.phoneNumber != null)
				return false;
		} else if (!phoneNumber.equals(other.phoneNumber))
			return false;
		if (version != other.version)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Contact [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", version=" + version
				+ "]";
	}

}
