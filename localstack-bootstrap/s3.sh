#!/bin/bash

createS3BucketIfNotExists() {
    set -x
    awslocal s3 mb s3://$1
    set +x
}

createS3BucketIfNotExists cnl-content-local
