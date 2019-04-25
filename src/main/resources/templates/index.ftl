<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as out>

<@c.page>
    <div>
    <@out.logout/>
        <span><a href="/user">All users</a></span>
    </div>
<div>
    <form method="post" enctype="multipart/form-data">
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <input type="text" name="text" placeholder="Input message"/>
        <input type="text" name="tag" placeholder="Tag"/>
        <input type="file" name="file">
        <button type="submit">Add</button>
    </form>
</div>
<div>Posts</div>
<form method="get" action="/index">
    <input type="text" name="tag" value="${tag?ifExists}">
    <button type="submit">Find</button>
</form>
<#list posts as post>
    <div>
        <b>${post.id}</b>
    <span>${post.text}</span>
    <i>${post.tag}</i>
        <strong>${post.authorName}</strong>
        <div>
            <#if post.fileneme??>
                <img src="/img/${post.filename}"
            </#if>
        </div>
    </div>
<#else>
    No posts
</#list>
</@c.page>