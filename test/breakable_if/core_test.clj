(ns breakable-if.core-test
  (:require [clojure.test :refer :all]
            [breakable-if.core :refer :all]))

(deftest a-test
  (testing "Flow of control without breaks."
    (is (= (iffy true 1 0) 1))
    (is (= (iffy false 1 0) 0)))
  (testing "Flow of control without return value and with breaks"
    (let [x (atom nil)]
      (iffy true
            (do
              (reset! x 1)
              (iffy-break)
              (reset! x 2)))
      (is (= @x 1)))
    (let [x (atom nil)]
      (iffy true
            (do
              (reset! x 1)
              (iffy-break)
              (reset! x 2))
            (throw (ex-info "Else body must not execute" {})))
      (is (= @x 1)))
    (let [x (atom 1)]
      (iffy false
            (do
              (reset! x 2)
              (iffy-break)
              (reset! x 3)))
      (is (= @x 1)))
    (let [x (atom nil)]
      (iffy false
            (throw (ex-info "Then body must not execute" {}))
            (do
              (reset! x 1)
              (iffy-break)
              (reset! x 2)))
      (is (= @x 1))))
  (testing "Flow of control with return value and with breaks"
    (is (= (iffy true
                 (do
                   (iffy-break 99)
                   1))
           99))
    (is (= (iffy false
                 (do
                   (iffy-break 100)
                   1))
           nil))
    (is (= (iffy true
                 (do
                   (iffy-break 101)
                   1)
                 2)
           101))
    (is (= (iffy false
                 1
                 (do
                   2
                   (iffy-break 102)
                   3))
           102))))
