(ns breakable-if.core)

(defmacro iffy
  "A dubious flow of control construct like clojure.core/if that one can break out of to deliberately
   continue execution after the construct."
  ([condition then-body]
   `(try
       (if ~condition ~then-body)
       (catch breakable_if.NonBreakableIfException e#
         (.getBreakReturnValue e#))))
  ([condition then-body else-body]
   `(try
      (if ~condition ~then-body ~else-body)
      (catch breakable_if.NonBreakableIfException e#
        (.getBreakReturnValue e#)))))

(defn iffy-break
  ([]
   (throw (breakable_if.NonBreakableIfException. (cast Object nil))))
  ([iffy-return-value]
   (throw (breakable_if.NonBreakableIfException. iffy-return-value))))