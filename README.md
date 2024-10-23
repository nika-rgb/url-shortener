# url-shortener

## Description
URL shortener service similar to tinyurl, service which generates shorter url from original URL

## Requirements

### Functional

* User should be able to generate short url given original long url
* User should be able to generate custom url if he/she prefers it
* Maximum allowed custom url size should be 16
* User should be able to use generated short url to redirect to original url
* User should be able to see metrics like most visited urls and etc.

### Non-functional
* Only authorized user should be able to use url shortener services
* Generated short url shouldn't be lost, tampered or expired
* Service should be able to support 100 million URL shortening requests per month
* Service uptime should 100%
* URL redirection shouldn't be fast and shouldn't degrade over time
* Service should support increasing load and shouldn't behave differently in peak times
* Service should be able scalable

### System design
Let's assume that we should support 200/1 read/write ration for our application. Since we have to support 100 million requests
per month that would mean that we should be able to support around 40 URL(100 mil / 31 / 24 / 60 / 60) creations per second.
With the above ration we get 8000 URL redirections per second.

Important metrics:
* write 40 URL/s
* redirection 8000 URL/s

Storage requirements:
Let's assume that we want this service to be running for 20 years that would mean we would need around 
500 bytes (for each shortened url) * (100 mil * 12 * 20) = 500 bytes * 24bil = 12 TB so that means that we will need around
12 TB of storage we'll also use internal cache with reasonable eviction and expiration policies to speed up the retrieval process and also to
minimize the database hits. We will use Cache-Aside strategy

### Development
During the development process, I will use DDD and TDD principles application will be deployed on AWS account. It's also
reasonable to write performance/load/stress tests for application to understand if the solution is suitable for given requirements.