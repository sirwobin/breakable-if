(ns breakable-if.NonBreakableIfException
  (:gen-class :extends RuntimeException
              :init    init
              :constructors {[Object] [String]}
              :state   state
              :methods [[getBreakReturnValue [] Object]]))

(defn -init [^Object return-value]
  [["You must only call iffy-break within an iffy body."] return-value])

(defn -getBreakReturnValue [this]
  (.state this))