/**
 *
 */
package com.grievance.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.ToString;

/**
 *
 */

@Entity
@ToString
public class Employee {
  @Id
  @Column(unique = true)
  private String email;

  private String password;

  /**
   * @return the name
   */
  public String getEmail() {
    return email;
  }

  /**
   * @param name the name to set
   */
  public void setEmail(String name) {
    this.email = name;
  }

  /**
   * @return the password
   */
  public String getPassword() {
    return password;
  }

  /**
   * @param password the password to set
   */
  public void setPassword(String password) {
    this.password = password;
  }
}
