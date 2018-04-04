
spring:
  profiles: default
  eureka:
    instance:
      hostname: localhost
    client:
      register-with-eureka: false
      fetch-registry: false
      service-url:
        defaultZone: http://localhost:9000/eureka 


spring:
  profiles: united-states
  eureka:
    instance:
      hostname: my-eureka-server-us.com    
    client:
      register-with-eureka: true
      fetch-registry: true       
      service-url:
        defaultZone: http://my-eureka-server-fr.com:9002/eureka/,http://my-eureka-server-vn.com:9003/eureka/


spring:
  profiles: france
  eureka:
    instance:
      hostname: my-eureka-server-fr.com      
    client:
      register-with-eureka: true
      fetch-registry: true       
        service-url:
          defaultZone: http://my-eureka-server-us.com:9001/eureka/,http://my-eureka-server-vn.com:9003/eureka/


spring:
  profiles: vietnam
    eureka:
      instance:
        hostname: my-eureka-server-vn.com    
      client:
        register-with-eureka: true
        fetch-registry: true   
        service-url:
          defaultZone: http://my-eureka-server-us.com:9001/eureka/,http://my-eureka-server-fr.com:9002/eureka/
