package hello;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by Fred on 2019-01-19.
 */
public class GoogleHomeService {
  private HibernateUtil hibernateUtil = new HibernateUtil();

  public void add(GoogleHomeRequest req) {
    EntityManager em = hibernateUtil.getEntityManager();
    em.getTransaction().begin();
    em.persist( req );
    em.getTransaction().commit();
    em.close();
  }

  public void update(GoogleHomeRequest req) {
    EntityManager em = hibernateUtil.getEntityManager();
    em.getTransaction().begin();
    em.merge( req );
    em.getTransaction().commit();
    em.close();
  }

  public GoogleHomeRequest get(long id) {
    EntityManager em = hibernateUtil.getEntityManager();
    em.getTransaction().begin();
    GoogleHomeRequest req = (GoogleHomeRequest) em.createQuery( "from GoogleHomeRequest req where req.id=" + id ).getSingleResult();
    em.getTransaction().commit();
    em.close();
    return req;
  }

  public List<GoogleHomeRequest> getAll() {
    EntityManager em = hibernateUtil.getEntityManager();
    em.getTransaction().begin();
    List list = em.createQuery( "from GoogleHomeRequest", GoogleHomeRequest.class ).getResultList();
    em.getTransaction().commit();
    em.close();
    return list;
  }

  public void deleteAll() {
    EntityManager em = hibernateUtil.getEntityManager();
    em.getTransaction().begin();
    em.createQuery( "delete from GoogleHomeRequest" ).executeUpdate();
    em.getTransaction().commit();
    em.close();
  }
}
