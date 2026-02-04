function pt=newton(tk,yk,t)
pt=eval_newton(tk,difdiv(tk,yk),t);
end
function ak=difdiv(tk,yk)

N=length(tk);
A=zeros(N,1);
A(:,1)=yk;
for k=2:N
    delta=k-1;
    for j=N:-1:k
        df=A(j)-A(j-1);
        dt=tk(j)-tk(j-delta);
        A(j) = df / dt;
    end
end
ak=A;
end

function pt=eval_newton(tk,ak,t)
N=length(ak);
pt=ak(N);
for k=N-1:-1:1
    pt=pt.*(t-tk(k))+ak(k);
end
end

