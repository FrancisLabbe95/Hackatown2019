package hello;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Fred on 2019-01-19.
 */
@Entity
@Table( name = "GoogleHomeRequests" )
public class GoogleHomeRequest {
  private long id;
  private String reqType;


  public GoogleHomeRequest() {
  }

  public GoogleHomeRequest(long id, String reqType) {
    this.id = id;
    this.reqType = reqType;
  }

  @Id
  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getReqType() {
    return reqType;
  }

  public void setReqType(String reqType) {
    this.reqType = reqType;
  }
}
