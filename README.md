# foreclojure-to-md

Scrapes your [4clojure.com](http://www.4clojure.com) solutions into a Markdown print for easier sharing.

## Installation

    $ lein uberjar

## Usage

Ensure you are connected to the internet and have obtained a SESSION_ID (the ring-session cookie value) from the [4clojure.com](http://www.4clojure.com) website and run:

    $ SESSION_ID=<session-id>
    $ java -jar target/uberjar/foreclojure-to-md-1.0.0-standalone.jar $SESSION_ID

To create a file with solutions called `README.md`:

    $ java -jar target/uberjar/foreclojure-to-md-1.0.0-standalone.jar $SESSION_ID > README.md

## Options

<session-id> A session-id obtained from the [4clojure.com](http://www.4clojure.com) website. I.e., the ring-session cookie value.

![Ring session ID](https://raw.githubusercontent.com/transducer/4clojure-to-md/master/resources/ring-session-id.png)

## TODO:

- Display description and tests content (this is bit difficult because of nested p- and a-tags)
- Obtain amount of problems from [4clojure.com](http://www.4clojure.com) (instead of using hard coded 161 value)
- Do not display non-existing problems (like 109)

## License

Copyright © 2018 transducer

Distributed under the MIT license.
