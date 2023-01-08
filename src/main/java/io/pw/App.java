package io.pw;

import io.pw.jdc.http.HttpMethod;
import org.json.JSONObject;

/**
 * Created by pwykowski
 */
public class App {

	public static void main(String[] args) {
		String resp = """
				HTTP/1.1 200 OK
				Api-Version: 1.41
				Content-Type: application/json
				Docker-Experimental: false
				Ostype: linux
				Server: Docker/20.10.22 (linux)
				Date: Sun, 01 Jan 2023 16:52:36 GMT
				Content-Length: 785
				    
				{"Platform":{"Name":""},"Components":[{"Name":"Engine","Version":"20.10.22","Details":{"ApiVersion":"1.41","Arch":"amd64","BuildTime":"2022-12-20T20:42:46.000000000+00:00","Experimental":"false","GitCommit":"42c8b31499","GoVersion":"go1.19.4","KernelVersion":"6.1.1-arch1-1","MinAPIVersion":"1.12","Os":"linux"}},{"Name":"containerd","Version":"v1.6.14","Details":{"GitCommit":"9ba4b250366a5ddde94bb7c9d1def331423aa323.m"}},{"Name":"runc","Version":"1.1.4","Details":{"GitCommit":""}},{"Name":"docker-init","Version":"0.19.0","Details":{"GitCommit":"de40ad0"}}],"Version":"20.10.22","ApiVersion":"1.41","MinAPIVersion":"1.12","GitCommit":"42c8b31499","GoVersion":"go1.19.4","Os":"linux","Arch":"amd64","KernelVersion":"6.1.1-arch1-1","BuildTime":"2022-12-20T20:42:46.000000000+00:00"}
				""";
		final int lastEnter = resp.lastIndexOf("\n");
		int lastIdx = 0;
		int idx;
		while ((idx = resp.indexOf("\n", lastIdx)) < lastEnter) {
			lastIdx = idx + 1;
		}
		System.out.println(new JSONObject(resp.substring(lastIdx)));
	}

}
