(ns backend.pageutils
  (:require [hiccup.html :as hiccup]))

(defn htroute
  "Ring route, returns a GET route with a handler that returns a string-encoded HTML page"
  [title args & body]
  {:get
   {:summary (str title "(html page)")}
   :handler
   (fn [& args]
     {:status 200
      :headers {"Content-Type" "text/html"}
      :body (str
             (hiccup/html
              [:head
               [:title title]
               [:link {:rel "stylesheet" :href "/static/tw.css"}]]
              [:body body]))})})

(defn htnroute
  "Return a `htroute` but with the navbar & a body container"
  [title args & body]
  (htroute title args
           [:div {:class "w-screen flex flex-col"} ;;navbar + body container
            [:div {:class "w-screen bg-zinc-100 flex flex-row items-center px-8 py-4"} ;; navbar
             [:div {:class "text-xl font-medium select-none"} "My App"]
             [:div {:class "ml-auto flex flex-row items-center space-x-6"}
              [:a {:class "text-zinc-900 hover:text-zinc-700" :href "/admin/"} "Home"]
              [:a {:class "text-zinc-900 hover:text-zinc-700" :href "/admin/about"} "About"]
              [:a {:class "text-zinc-900 hover:text-zinc-700" :href "/admin/contact"} "Contact"]]]
            [:div {:class "w-screen flex flex-col items-center p-8"} ;; body container
             body ;; body
             ]]))
