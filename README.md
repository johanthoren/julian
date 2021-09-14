# xyz.thoren/julian

A Clojure(Script) library designed to allow conversions between Julian Day
Number and Gregorian Time.

Julian Day Numbers are represented by a float, and Gregorian Time is represented
by a map containing the keys `:year`, `:month`, `:day`, `:hour`, `:minute`, and
`:second`.

## Usage

[![Clojars Project](https://img.shields.io/clojars/v/xyz.thoren/julian.svg)](https://clojars.org/xyz.thoren/julian)

Require:

``` clojure
(:require [xyz.thoren.julian :as jdn])
```

Examples:

``` clojure
(jdn/to-gregorian 2459293.5)
;; => {:year 2021, :month 3, :day 20, :hour 0, :minute 0, :second 0}
```

``` clojure
(jdn/to-julian :year 2021, :month 3, :day 20, :hour 0, :minute 0, :second 0)
;; => 2459293.5
```

## Acknowledgements

All calculations are based on the excellent work [Astronomical
Algorithms](https://openlibrary.org/works/OL2009494W/Astronomical_algorithms) by
[Jean Meeus](https://en.wikipedia.org/wiki/Jean_Meeus).

## License

```
ISC License

Copyright (c) 2021 Johan Thorén

Permission to use, copy, modify, and/or distribute this software for any
purpose with or without fee is hereby granted, provided that the above
copyright notice and this permission notice appear in all copies.

THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR DISCLAIMS ALL WARRANTIES WITH
REGARD TO THIS SOFTWARE INCLUDING ALL IMPLIED WARRANTIES OF MERCHANTABILITY
AND FITNESS. IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY SPECIAL, DIRECT,
INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES WHATSOEVER RESULTING FROM
LOSS OF USE, DATA OR PROFITS, WHETHER IN AN ACTION OF CONTRACT, NEGLIGENCE OR
OTHER TORTIOUS ACTION, ARISING OUT OF OR IN CONNECTION WITH THE USE OR
PERFORMANCE OF THIS SOFTWARE.
```