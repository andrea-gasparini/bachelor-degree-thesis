function apiRequest<T>(url : string, data : Object = {}) : Promise<T>
{
    return new Promise((resolve, reject) => {
        $.ajax({
            url, 
            method: "post",
            data,
            headers: { Authorization: LocalStorage.getTokenValue() },
            success: (res : RestResponse<T>) =>
            {
                LocalStorage.setToken(res.accessToken);
                resolve(res.body);
            },
            error: (jqXHR, status, err) =>
            {
                reject(Utils.parseResponse(jqXHR, status, err));
            }
        });
    });
}