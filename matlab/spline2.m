function s=spline2(xk,fk,x,d1)
    
    % Longitud tabla (N) y espaciado (h) entre datos
    N=length(xk);
    h=abs(xk(1)-xk(end))/(N-1);
    
    % Calculo de los N coeficientes {mk}
    m=0*xk;
    m(1)=(h/2)*d1;
    for k=2:N
        m(k) = (fk(k) - fk(k-1)) - m(k-1);
    end
    
    % Evaluación del spline ss en los puntos de xx
    s=0*x; % Reserva vector de salida
    for i=1:length(x)
        todo=(x(i)-xk(1))/h;
        k=floor(todo)+1;
        u=mod(todo,1);
        if u==0
            s(i) = fk(k);
        else
            s(i) = fk(k) + m(k) + m(k+1)* u^2 - m(k)*(1-u)^2;
        end
    end

end