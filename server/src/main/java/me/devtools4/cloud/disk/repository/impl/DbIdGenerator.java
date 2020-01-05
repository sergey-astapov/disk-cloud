package me.devtools4.cloud.disk.repository.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import javax.persistence.EntityManager;
import me.devtools4.cloud.disk.repository.IdGenerator;

public class DbIdGenerator implements IdGenerator {

  private final EntityManager em;
  private final String nextValueQuery;

  public DbIdGenerator(EntityManager em, String nextValueQuery) {
    this.em = em;
    this.nextValueQuery = nextValueQuery;
  }

  @Override
  public Long nextId() {
    return nextId(em, nextValueQuery);
  }

  private static Long nextId(EntityManager em, String nextValueQuery) {
    Object obj = em.createNativeQuery(nextValueQuery).getSingleResult();
    if (obj instanceof BigInteger) {
      return ((BigInteger) obj).longValue();
    } else if (obj instanceof BigDecimal) {
      return ((BigDecimal) obj).longValue();
    } else {
      throw new IllegalArgumentException("Unsupported result format, obj=" + obj);
    }
  }
}