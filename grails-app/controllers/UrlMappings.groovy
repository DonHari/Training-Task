import java.nio.file.AccessDeniedException

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?" {
            constraints {
                // apply constraints here
            }
        }

        "/"(view: "/index")
        "/customError"(view: "/customError")
        "403"(controller: "error", action: "accessDenied", exception: AccessDeniedException)
        "500"(controller: "error", action: "index")
//        "500"(view: '/error')
        "404"(view: '/notFound')
    }
}
