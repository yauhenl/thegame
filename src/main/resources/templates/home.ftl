<#include "components/header.ftl">
<a href="/createWorld" role="button" class="btn btn-primary">Create world</a>
<div class="container">
    <table class="table">
        <tbody>
        <#list worlds as world>
            <tr>
                <td>${world.id}</td>
                <td><a href="/game?id=${world.id}" role="button" class="btn btn-primary">Enter</a></td>
            </tr>
        </#list>
        </tbody>
    </table>
</div>
<#include "components/footer.ftl">