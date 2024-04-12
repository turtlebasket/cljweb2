(ns backend.main
  (:require [aleph.http :as http]
            [backend.components :refer [button]]
            [backend.pageutils :refer [htnroute]]
            [reitit.ring :as ring]))

(def router
  (ring/ring-handler
   (ring/router
    [["/static/*" (ring/create-file-handler {:root "static"})]
     ["/admin"
      ["/about"
       (htnroute "About" []
                 [:div {:class "flex flex-col align-center items-y-5"}
                  [:h1 "About us"]
                  (button "hello world" "/admin/contact")])]

      ["/contact"
       (htnroute "Contact" []
                 [:div [:h1 "Contact us"]])]

      ["/"
       (htnroute "Home" []
                 [:div [:h1 "Welcome to the home page"]])]]

     ["/api"
      ["/ping" {:get {} :handler (fn [req] {:status 200 :body "pong"})}]]])
   (ring/create-default-handler)))

(defn -main []
  (http/start-server router {:port 3000}))
