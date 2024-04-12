(ns backend.components)

(defn button
  "Return a styled button"
  [text href]
  [:a {:class "bg-zinc-500 text-white px-4 py-2 rounded-md hover:bg-zinc-700" :href href} text])
