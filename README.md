# React | Springboot | AWS | Github Actions

This project consists of a frontend React application deployed on S3 and a Spring Boot backend API deployed on EC2


## Deployment

##### Frontend

The frontend React application is automatically deployed to S3 using GitHub Actions. The workflow is defined in `.github/workflows/cd_react_frontend.yml`.

##### Backend

The backend Spring Boot application is built and pushed to Docker Hub then deployed to EC2 using GitHub Actions. The workflow is defined in `.github/workflows/cd_spring_backend.yml`.

---

## Frontend

The frontend React application is deployed on S3 and can be accessed here:
[http://oci-intern-challenge-bucket.s3-website-ap-southeast-1.amazonaws.com](http://oci-intern-challenge-bucket.s3-website-ap-southeast-1.amazonaws.com)

## Backend

The backend Spring Boot API can be tested with the following endpoint:
[http://13.228.168.109:8080/api/v1/coin-change](http://13.228.168.109:8080/api/v1/coin-change)


<br>


#### Example Request

To test the coin change API, you can send a POST request to the backend with the following JSON body:

```json
{
    "amount": 7.01,
    "denominations": [0.1, 0.5, 0.01, 1, 5, 10]
}
```

#### Example Response

The API will respond with the minimum number of coins needed to make the given amount.

```json
{
    "coins": [
        0.01,
        1.0,
        1.0,
        5.0
    ]
}
```

## Docker

The Docker image for the Spring Boot backend is available at:

```text
keiyam/oracle_spring_backend/70cb26df570de4f56f22738ad970308986904e1a
```

You can pull and run the Docker image using the following commands:

```bash
docker pull keiyam/oracle_spring_backend:70cb26df570de4f56f22738ad970308986904e1a
docker run -d -p 8080:8080 keiyam/oracle_spring_backend:70cb26df570de4f56f22738ad970308986904e1a
```

On arm64 (Apple Silicon Macs)
```bash
docker run --platform linux/amd64 -d -p 8080:8080 keiyam/oracle_spring_backend:70cb26df570de4f56f22738ad970308986904e1a
```


