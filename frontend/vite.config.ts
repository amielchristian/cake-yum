import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react-swc'

// https://vite.dev/config/
export default defineConfig({
  base: '/cake-yum/',
  plugins: [react()],
  appType: 'spa',
})
