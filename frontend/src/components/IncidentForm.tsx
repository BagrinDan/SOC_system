import React, { useState } from 'react';
import { AlertTriangle, Send, CheckCircle2, ShieldAlert } from 'lucide-react';

export const VulnerabilityType = {
     UNSPECIFIED: 0,
     SQLI: 1,
     XSS: 2,
} as const;

export type VulnerabilityType = typeof VulnerabilityType[keyof typeof VulnerabilityType];

export interface IncidentData {
     type: VulnerabilityType;
     sourceIp: string;
     payload: string;
     targetEndpoint: string;
}

export const IncidentForm: React.FC = () => {
     const [formData, setFormData] = useState<IncidentData>({
          type: VulnerabilityType.SQLI,
          sourceIp: '',
          payload: '',
          targetEndpoint: '',
     });

     const [status, setStatus] = useState<'idle' | 'submitting' | 'success' | 'error'>('idle');

     const handleChange = (
          e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement | HTMLTextAreaElement>
     ) => {
          const { name, value } = e.target;
          setFormData((prev) => ({
               ...prev,
               [name]: name === 'type' ? Number(value) : value,
          }));
     };

     const handleSubmit = async (e: React.FormEvent) => {
          e.preventDefault();
          setStatus('submitting');

          const endpoint = formData.type === VulnerabilityType.SQLI
               ? 'http://localhost:8091/api/sqli'
               : 'http://localhost:8092/api/xss';

          const payload = {
               source_ip: formData.sourceIp,
               payload: formData.payload,
               target_endpoint: formData.targetEndpoint,
          };

          try {
               const response = await fetch(endpoint, {
                    method: 'POST',
                    headers: { 'Content-Type': 'application/json' },
                    body: JSON.stringify(payload),
               });

               if (response.ok) {
                    setStatus('success');
                    setFormData({
                         type: VulnerabilityType.SQLI,
                         sourceIp: '',
                         payload: '',
                         targetEndpoint: '',
                    });
               } else {
                    setStatus('error');
               }
          } catch (error) {
               console.error('Error submitting incident:', error);
               setStatus('error');
          }
     };

     return (
          <section className="incident-form" aria-labelledby="incident-form-title">
               <header className="incident-form__header">
                    <ShieldAlert className="incident-form__icon" />
                    <div>
                         <h1 id="incident-form-title" className="incident-form__title">SOC Incident Reporter</h1>
                         <p className="incident-form__subtitle">Регистрация событий безопасности (gRPC Ingress)</p>
                    </div>
               </header>

               <form onSubmit={handleSubmit} className="incident-form__form" noValidate>
                    <label className="form-row">
                         <span className="form-label">Тип уязвимости (VulnerabilityType)</span>
                         <select name="type" value={formData.type} onChange={handleChange} className="form-control">
                              <option value={VulnerabilityType.SQLI}>SQL Injection (SQLi)</option>
                              <option value={VulnerabilityType.XSS}>Cross-Site Scripting (XSS)</option>
                         </select>
                    </label>

                    <label className="form-row">
                         <span className="form-label">IP-адрес источника (Source IP)</span>
                         <input
                              type="text"
                              name="sourceIp"
                              value={formData.sourceIp}
                              onChange={handleChange}
                              placeholder="192.168.1.100"
                              required
                              pattern="^((25[0-5]|(2[0-4]|1\d|[1-9]|)\d)\.){3}(25[0-5]|(2[0-4]|1\d|[1-9]|)\d)$"
                              className="form-control"
                         />
                    </label>

                    <label className="form-row">
                         <span className="form-label">Целевой эндпоинт (Target Endpoint)</span>
                         <input
                              type="text"
                              name="targetEndpoint"
                              value={formData.targetEndpoint}
                              onChange={handleChange}
                              placeholder="/api/v1/login"
                              required
                              className="form-control"
                         />
                    </label>

                    <label className="form-row">
                         <span className="form-label">Полезная нагрузка (Payload)</span>
                         <textarea
                              name="payload"
                              value={formData.payload}
                              onChange={handleChange}
                              placeholder="Payload / сигнатура инцидента..."
                              rows={4}
                              required
                              className="form-control form-control--textarea"
                         />
                    </label>

                    {status === 'success' && (
                         <div className="status status--success" role="status">
                              <CheckCircle2 />
                              <span>Инцидент успешно отправлен в брокер сообщений.</span>
                         </div>
                    )}

                    {status === 'error' && (
                         <div className="status status--error" role="alert">
                              <AlertTriangle />
                              <span>Ошибка при отправке инцидента. Проверьте соединение.</span>
                         </div>
                    )}

                    <div className="form-actions">
                         <button type="submit" disabled={status === 'submitting'} className="btn btn--primary">
                              <Send />
                              <span>{status === 'submitting' ? 'Отправка...' : 'Отправить в Publisher'}</span>
                         </button>
                    </div>
               </form>
          </section>
     );
};