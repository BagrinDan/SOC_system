# SOC_system


======================
    Arhitectura Lab 2
======================

> Tema: Sistem SOC pentru inregistrarea incidentelor 

> Web Server - Nginx

> Broker — Java

> Pub: GO 

> Sub: Python

> K3s/Docker:
    > Docker: Broker, Pub 
    > K3s: Sub, Databasese

> Storage
    *Transient (In-memory)

    *Persistent (On-Disk) - PostgreSQL (SQLi), 
                            MariaDB (XSS)


> Deployment (?)
    * Cloudfare 


> Roadmap:
    1. .proto
    2. broker
    3. sub and db
    4. pub
    5. frontend
    6. nginx & deploy