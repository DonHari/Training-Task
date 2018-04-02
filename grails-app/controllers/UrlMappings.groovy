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
        "500"(controller: "error", action: "accessDenied", exception: AccessDeniedException)
        "500"(view: '/error')
        "404"(view: '/notFound')
    }
}
