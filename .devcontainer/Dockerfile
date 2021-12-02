## Dockerfile for a haskell environment
FROM haskell:8.6.5

RUN stack upgrade
RUN stack --resolver ghc-8.6.5 setup
RUN stack config set resolver lts-14.27
RUN stack install intero