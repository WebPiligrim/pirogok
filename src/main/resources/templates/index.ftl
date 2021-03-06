<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as out>

<@c.page>
    <div class="form-row">
        <div class="form-group col-md-6">
            <form method="get" action="/index" class="form-inline">
                <input type="text" name="tag" class="form-control" value="${tag?ifExists}" placeholder="Search by tag">
                <button type="submit" class="btn btn-primary ml-2">Search</button>
            </form>
        </div>
    </div>
    <a class="btn btn-primary" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
        Create new post
    </a>
    <div class="collapse" id="collapseExample">
        <div class="form-group mt-3">
            <form method="post" enctype="multipart/form-data">
                <div class="form-group">
                    <input type="text" class="form-control" name="text" placeholder="Input message" />
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" name="tag" placeholder="Tag">
                </div>
                <div class="form-group">
                    <div class="custom-file">
                        <input type="file" name="file" id="customFile">
                        <label class="custom-file-label" for="customFile">Choose file</label>
                    </div>
                </div>
                <input type="hidden" name="_csrf" value="${_csrf.token}" />
                <div class="form-group">
                    <button type="submit" class="btn btn-primary">Add</button>
                </div>
            </form>
        </div>
    </div>
    <div class="card-columns">
        <#list posts as post>
            <div class="card my-3">
                <#if post.filename??>
                    <img src="/img/${post.filename}" class="card-img-top">
                </#if>
                <div class="m-2">
                    <span>${post.text}</span>
                    <i>${post.tag}</i>
                </div>
                <div class="card-footer text-muted">
                    ${post.authorName}
                </div>
            </div>
        <#else>
            No post
        </#list>
    </div>
</@c.page>