let token=localStorage.getItem("accessToken");
const out=(id,x)=>document.getElementById(id).textContent=typeof x==="string"?x:JSON.stringify(x,null,2);
async function api(path,opt={}){opt.headers={...(opt.headers||{}),"Content-Type":"application/json"};if(token)opt.headers.Authorization="Bearer "+token;const r=await fetch(path,opt);let x;try{x=await r.json()}catch{ x=await r.text()}if(!r.ok)throw new Error(JSON.stringify(x));return x}
async function login(){try{let x=await api("/api/auth/login",{method:"POST",body:JSON.stringify({username:username.value,password:password.value})});token=x.accessToken;localStorage.setItem("accessToken",token);out("authOut",x)}catch(e){out("authOut",e.message)}}
async function register(){try{out("authOut",await api("/api/auth/register",{method:"POST",body:JSON.stringify({username:username.value,gmailAddress:prompt("Gmail address"),password:password.value})}))}catch(e){out("authOut",e.message)}}
function connectGmail(){location.href="/oauth/google"}
async function sync(){try{out("gmailOut",await api("/api/gmail/sync",{method:"POST"}))}catch(e){out("gmailOut",e.message)}}
async function dashboard(){try{out("dashOut",await api("/api/dashboard/summary"))}catch(e){out("dashOut",e.message)}}
async function alerts(){try{out("alertOut",await api("/api/alerts"))}catch(e){out("alertOut",e.message)}}
