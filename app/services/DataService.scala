package services

import com.redis.RedisClient
import javax.inject.Singleton

@Singleton
class DataService() extends interfaces.DataService {
  override def Get(key: String): String = {
    rc.get(key) match {
      case Some(x) => x
      case None => "Not found"
    }
  }

  override def Save(key: String, value: String): Boolean = {
    rc.set(key, value)
  }

  override def Delete(key: String): Boolean = {
    rc.del(key) match {
      case Some(x) => true
      case None => false
    }
  }

  private val rc = new RedisClient("localhost", 6379)

}
