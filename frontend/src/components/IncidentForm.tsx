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
      ? 'http://localhost:8091/api/sqli' // Pub1 (SQLi)
      : 'http://localhost:8092/api/xss';  // Pub2 (XSS)

    const payload = {
      source_ip: formData.sourceIp,
      payload: formData.payload,
      target_endpoint: formData.targetEndpoint,
    };

    try {
      const response = await fetch(endpoint, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
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
    <div className="max-w-xl mx-auto mt-10 p-6 bg-slate-900 border border-slate-800 rounded-xl shadow-2xl text-slate-100 font-sans">
      <div className="flex items-center gap-3 mb-6 pb-4 border-b border-slate-800">
        <ShieldAlert className="w-7 h-7 text-red-500" />
        <div>
          <h2 className="text-xl font-bold">SOC Incident Reporter</h2>
          <p className="text-xs text-slate-400">Регистрация событий безопасности (gRPC Ingress)</p>
        </div>
      </div>

      <form onSubmit={handleSubmit} className="space-y-4">
        {/* VulnerabilityType type = 1 */}
        <div>
          <label className="block text-sm font-medium text-slate-300 mb-1">
            Тип уязвимости (VulnerabilityType)
          </label>
          <select
            name="type"
            value={formData.type}
            onChange={handleChange}
            className="w-full px-3 py-2 bg-slate-800 border border-slate-700 rounded-lg text-slate-200 focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-transparent transition"
          >
            <option value={VulnerabilityType.SQLI}>SQL Injection (SQLi)</option>
            <option value={VulnerabilityType.XSS}>Cross-Site Scripting (XSS)</option>
          </select>
        </div>

        {/* string source_ip = 2 */}
        <div>
          <label className="block text-sm font-medium text-slate-300 mb-1">
            IP-адрес источника (Source IP)
          </label>
          <input
            type="text"
            name="sourceIp"
            value={formData.sourceIp}
            onChange={handleChange}
            placeholder="192.168.1.100"
            required
            pattern="^((25[0-5]|(2[0-4]|1\d|[1-9]|)\d)\.){3}(25[0-5]|(2[0-4]|1\d|[1-9]|)\d)$"
            className="w-full px-3 py-2 bg-slate-800 border border-slate-700 rounded-lg text-slate-200 placeholder-slate-500 focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-transparent transition"
          />
        </div>

        {/* string target_endpoint = 4 */}
        <div>
          <label className="block text-sm font-medium text-slate-300 mb-1">
            Целевой эндпоинт (Target Endpoint)
          </label>
          <input
            type="text"
            name="targetEndpoint"
            value={formData.targetEndpoint}
            onChange={handleChange}
            placeholder="/api/v1/login"
            required
            className="w-full px-3 py-2 bg-slate-800 border border-slate-700 rounded-lg text-slate-200 placeholder-slate-500 focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-transparent transition"
          />
        </div>

        {/* string payload = 3 */}
        <div>
          <label className="block text-sm font-medium text-slate-300 mb-1">
            Полезная нагрузка (Payload)
          </label>
          <textarea
            name="payload"
            value={formData.payload}
            onChange={handleChange}
            placeholder="Payload / сигнатура инцидента..."
            rows={3}
            required
            className="w-full px-3 py-2 bg-slate-800 border border-slate-700 rounded-lg text-slate-200 placeholder-slate-500 font-mono text-sm focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-transparent transition"
          />
        </div>

        {/* Status messages */}
        {status === 'success' && (
          <div className="flex items-center gap-2 p-3 bg-emerald-950/50 border border-emerald-800 text-emerald-400 rounded-lg text-sm">
            <CheckCircle2 className="w-5 h-5 flex-shrink-0" />
            <span>Инцидент успешно отправлен в брокер сообщений.</span>
          </div>
        )}

        {status === 'error' && (
          <div className="flex items-center gap-2 p-3 bg-red-950/50 border border-red-800 text-red-400 rounded-lg text-sm">
            <AlertTriangle className="w-5 h-5 flex-shrink-0" />
            <span>Ошибка при отправке инцидента. Проверьте соединение.</span>
          </div>
        )}

        {/* Submit button */}
        <button
          type="submit"
          disabled={status === 'submitting'}
          className="w-full flex items-center justify-center gap-2 py-2.5 px-4 bg-blue-600 hover:bg-blue-500 disabled:bg-slate-700 text-white font-medium rounded-lg transition duration-200 shadow-lg shadow-blue-600/20"
        >
          <Send className="w-4 h-4" />
          {status === 'submitting' ? 'Отправка...' : 'Отправить в Publisher'}
        </button>
      </form>
    </div>
  );
};